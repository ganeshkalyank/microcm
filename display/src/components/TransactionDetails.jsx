import { useEffect, useState } from "react";
import { entryapi } from "../utils/apis";
import { useParams } from "react-router-dom";
import {
  CartesianGrid,
  Label,
  Legend,
  Line,
  LineChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";
import ReactFlow, {
  useNodesState,
  useEdgesState,
  Background,
  Controls,
  ReactFlowProvider,
} from "reactflow";
import "reactflow/dist/style.css";
import Dagre from "@dagrejs/dagre";

const g = new Dagre.graphlib.Graph().setDefaultEdgeLabel(() => ({}));

const getLayoutedElements = (nodes, edges) => {
  g.setGraph({ rankdir: "TB", nodesep: 200, ranksep: 100 });

  edges.forEach((edge) => g.setEdge(edge.source, edge.target));
  nodes.forEach((node) => g.setNode(node.id, node));

  Dagre.layout(g);

  return {
    nodes: nodes.map((node) => {
      const { x, y } = g.node(node.id);

      return { ...node, position: { x, y } };
    }),
    edges,
  };
};

const TransactionDetails = () => {
  const [spans, setSpans] = useState([]);
  const [averageResponseTime, setAverageResponseTime] = useState(0);
  const [nodes, setNodes, onNodesChange] = useNodesState([]);
  const [edges, setEdges, onEdgesChange] = useEdgesState([]);

  const { tid } = useParams();

  const constructGraph = (data) => {
    let nodelist = [];
    let edgeslist = [];
    data.forEach((span) => {
      nodelist.push({
        id: `${span.spanId}`,
        data: { label: span.requestedService },
        position: { x: 0, y: 0 },
      });
      if (span.parentService) {
        let parentId = data.find(
          (s) => s.requestedService === span.parentService
        ).spanId;
        edgeslist.push({
          id: `${span.spanId}-${parentId}`,
          source: `${parentId}`,
          target: `${span.spanId}`,
          animated: true,
        });
      }
    });
    setNodes(getLayoutedElements(nodelist, edgeslist).nodes);
    setEdges(getLayoutedElements(nodelist, edgeslist).edges);
  };

  useEffect(() => {
    const fetchSpans = async () => {
      try {
        const response = await entryapi.get(`/span/${tid}`);
        setSpans(response.data.data);
        constructGraph(response.data.data);
      } catch (error) {
        console.error(error);
        setSpans([]);
      }
    };
    fetchSpans();
  }, [tid]);

  useEffect(() => {
    setAverageResponseTime(
      spans.reduce((acc, span) => acc + span.responseTime, 0) / spans.length
    );
  }, [spans]);

  return (
    <div className="px-5 py-10 md:p-20">
      <p className="my-5">
        Average Response Time: {averageResponseTime.toFixed(2)} ms
      </p>
      <div className="grid grid-cols-2">
        <div>
          <h3 className="text-subheading text-foreground">Call Graph</h3>
          <div className="bg-primary w-20 h-1"></div>
          <div className="mt-5 md:p-5 flex justify-center">
            <div className="h-96 w-full">
              <ReactFlowProvider>
                <ReactFlow
                  nodes={nodes}
                  edges={edges}
                  onNodesChange={onNodesChange}
                  onEdgesChange={onEdgesChange}
                  proOptions={{
                    hideAttribution: true,
                  }}
                  fitView
                >
                  <Background />
                  <Controls />
                </ReactFlow>
              </ReactFlowProvider>
            </div>
          </div>
        </div>
        <div>
          <h3 className="text-subheading text-foreground mt-5">
            Response Time Chart
          </h3>
          <div className="bg-primary w-20 h-1"></div>
          <div className="mt-5 md:p-5 flex justify-center">
            <ResponsiveContainer width="100%" height={384}>
              <LineChart
                data={spans}
                margin={{ top: 15, right: 30, left: 20, bottom: 5 }}
              >
                <Line type="monotone" dataKey="responseTime" stroke="#8884d8" />
                <CartesianGrid stroke="#ccc" />
                <XAxis dataKey="requestedService">
                  <Label
                    value="Requested Service"
                    offset={-5}
                    position="insideBottom"
                  />
                </XAxis>
                <YAxis
                  label={{
                    value: "Response Time (ms)",
                    angle: -90,
                    position: "insideLeft",
                  }}
                />
                <Tooltip />
                <Legend verticalAlign="top" height={30} />
              </LineChart>
            </ResponsiveContainer>
          </div>
        </div>
      </div>
      <h3 className="text-subheading text-foreground">Spans</h3>
      <div className="bg-primary w-20 h-1"></div>
      {spans.length === 0 ? (
        <p className="text-foreground mt-5">
          No spans available or network error.
        </p>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-5 mt-5">
          {spans.map((span, id) => (
            <div
              key={id}
              className={
                "flex justify-between p-5 rounded-lg shadow-md my-2" +
                (span.responseTime > averageResponseTime
                  ? " bg-red-200"
                  : " bg-white")
              }
            >
              <div>
                <p className="text-foreground">Span ID: {span.spanId}</p>
                <p className="text-foreground">
                  Transaction ID: {span.transactionId}
                </p>
                <p className="text-foreground">
                  Response Time: {span.responseTime} ms
                </p>
                <p className="text-foreground">
                  Requested Service: {span.requestedService}
                </p>
                <p className="text-foreground">
                  Parent Service: {span.parentService}
                </p>
                <p className="text-foreground">
                  Invocation Date Time:{" "}
                  {new Date(span.invocationDateTime).toUTCString()}
                </p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default TransactionDetails;
