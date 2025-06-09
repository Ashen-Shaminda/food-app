import { createContext, useEffect, useState } from "react";
import { fetchFoodList } from "../services/foodServices";

export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
	const [foodList, setFoodList] = useState([]);

	const contextValue = {
		foodList,
	};

	useEffect(() => {
		const loadData = async () => {
			const foodList = await fetchFoodList();
			setFoodList(foodList);
		};

		loadData();
	}, []);

	return (
		<StoreContext.Provider value={contextValue}>
			{props.children}
		</StoreContext.Provider>
	);
};
