import React from "react";
import Header from "../../components/Header/Header";
import ExploreMenu from "../../components/ExploreMenu/ExploreMenu";
import FoodDisplay from "../../components/FoodDisplay/FoodDisplay";

const Home = () => {
	return (
		<main className="container-fluid">
			<Header />
			<ExploreMenu />
			<FoodDisplay />
		</main>
	);
};

export default Home;
