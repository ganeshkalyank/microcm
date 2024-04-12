import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { entryapi } from "../utils/apis";

const Transactions = () => {
  const [transactions, setTransactions] = useState([]);

  const fetchTransactions = async () => {
    try {
      const response = await entryapi.get("/transactions");
      setTransactions(response.data.data);
    } catch (error) {
      console.error(error);
      setTransactions([]);
    }
  };

  useEffect(() => {
    fetchTransactions();
  }, []);

  return (
    <div className="px-5 py-10 md:p-20">
      <h3 className="text-subheading text-foreground">Transactions</h3>
      <div className="bg-primary w-20 h-1"></div>
      {transactions.length === 0 ? (
        <p className="text-foreground mt-5">
          No transactions available or network error.
        </p>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-5 mt-5">
          {transactions.map((transaction, id) => (
            <div
              key={id}
              className="flex justify-between p-5 bg-white rounded-lg shadow-md my-2"
            >
              <div>
                <p className="text-foreground">
                  Transaction ID: {transaction.transactionId}
                </p>
                <p className="text-foreground">
                  Requested Service: {transaction.requestedService}
                </p>
                <p className="text-foreground">
                  Invocation Time:{" "}
                  {new Date("2021-09-05T00:00:00").toUTCString()}
                </p>
              </div>
              <Link
                to={`/transactions/${transaction.transactionId}`}
                className="bg-primary flex items-center text-white px-5 py-2 rounded-lg"
              >
                View
              </Link>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Transactions;
