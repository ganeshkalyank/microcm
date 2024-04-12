import { Link } from "react-router-dom";

const Landing = () => {
  return (
    <div className="bg-white p-5 md:p-10 flex flex-col justify-center items-center text-center drop-shadow-lg">
      <Link to="/">
        <h1 className="text-heading font-serif font-bold text-primary">
          MicroCM
        </h1>
      </Link>
      <p className="text-subheading">
        A cloud monitoring architecture for microservice invocation.
      </p>
      <p>Data Display Module</p>
    </div>
  );
};

export default Landing;
