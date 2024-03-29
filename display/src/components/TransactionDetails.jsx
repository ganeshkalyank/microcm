import { useEffect, useState } from "react"
import { entryapi } from "../utils/apis"
import { useParams } from "react-router-dom"

const TransactionDetails = () => {

    const [spans, setSpans] = useState([])
    const [averageResponseTime, setAverageResponseTime] = useState(0)

    const { tid } = useParams();

    useEffect(() => {
        const fetchSpans = async () => {
            try {
                const response = await entryapi.get(`/span/${tid}`)
                console.log(response)
                setSpans(response.data.data)
                setAverageResponseTime(spans.reduce((acc, span) => acc + span.responseTime, 0) / spans.length)
            } catch (error) {
                console.error(error)
                setSpans([])
            }
        }
        fetchSpans()
    }, [spans, tid])

    return (
        <div className="px-5 py-10 md:p-20">
            <h3 className="text-subheading text-foreground">Spans</h3>
            <div className="bg-primary w-20 h-1"></div>
            {
                spans.length === 0 ? (
                    <p className="text-foreground mt-5">No spans available or network error.</p>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-5 mt-5">
                        {spans.map((span, id) => (
                            <div key={id} className={"flex justify-between p-5 rounded-lg shadow-md my-2" + (span.responseTime > averageResponseTime ? " bg-red-200" : " bg-white")}>
                                <div>
                                    <p className="text-foreground">Span ID: {span.spanId}</p>
                                    <p className="text-foreground">Transaction ID: {span.transactionId}</p>
                                    <p className="text-foreground">Response Time: {span.responseTime} ms</p>
                                    <p className="text-foreground">Requested Service: {span.requestedService}</p>
                                    <p className="text-foreground">Parent Service: {span.parentService}</p>
                                    <p className="text-foreground">Invocation Date Time: {span.invocationDateTime}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                )
            }
        </div>
    )
}

export default TransactionDetails
