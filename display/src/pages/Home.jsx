import Landing from "../components/Landing";
import Transactions from "../components/Transactions";

const Home = () => {
    return (
        <div className="bg-background min-h-screen">
            <Landing />
            <Transactions />
        </div>
    );
}

export default Home;
