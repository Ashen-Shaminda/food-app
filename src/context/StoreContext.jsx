import { createContext, useEffect, useState } from "react";
import { fetchFoodList } from "../services/foodServices";

// TODO : fix the warning.
export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
	const [foodList, setFoodList] = useState([]);
	const [quantities, setQuantities] = useState({});
	const [token, setToken] = useState("");

	const increaseQuantity = (foodId) => {
		setQuantities((prev) => ({ ...prev, [foodId]: (prev[foodId] || 0) + 1 }));
	};
	const decreaseQuantity = (foodId) => {
		setQuantities((prev) => ({
			...prev,
			[foodId]: prev[foodId] < 0 ? 0 : prev[foodId] - 1,
		}));
	};

	const removeItems = (foodId) => {
		setQuantities((prev) => {
			const updatedQuantities = { ...prev };
			delete updatedQuantities[foodId];

			return updatedQuantities;
		});
	};

	const contextValue = {
		foodList,
		increaseQuantity,
		decreaseQuantity,
		quantities,
		removeItems,
		token,
		setToken,
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
