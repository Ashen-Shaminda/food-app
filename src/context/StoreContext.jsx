import { createContext, useEffect, useState } from "react";
import { fetchFoodList } from "../services/foodServices";
import {
	addQuantity,
	getCartData,
	removeQuantity,
} from "../services/cartServices";

// TODO : fix the warning.
export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
	const [foodList, setFoodList] = useState([]);
	const [quantities, setQuantities] = useState({});
	const [token, setToken] = useState("");

	const increaseQuantity = async (foodId) => {
		setQuantities((prev) => ({ ...prev, [foodId]: (prev[foodId] || 0) + 1 }));

		await addQuantity(foodId, token);
	};

	const decreaseQuantity = async (foodId) => {
		setQuantities((prev) => ({
			...prev,
			[foodId]: prev[foodId] < 0 ? 0 : prev[foodId] - 1,
		}));

		await removeQuantity(foodId, token);
	};

	const removeItems = (foodId) => {
		setQuantities((prev) => {
			const updatedQuantities = { ...prev };
			delete updatedQuantities[foodId];

			return updatedQuantities;
		});
	};

	const loadCartData = async (token) => {
		const response = await getCartData(token);

		setQuantities(response.data.items);
	};

	const contextValue = {
		foodList,
		increaseQuantity,
		decreaseQuantity,
		quantities,
		setQuantities,
		removeItems,
		token,
		setToken,
		loadCartData,
	};

	useEffect(() => {
		const loadData = async () => {
			const foodList = await fetchFoodList();
			setFoodList(foodList);
			if (localStorage.getItem("token")) {
				setToken(localStorage.getItem("token"));
				await loadCartData(localStorage.getItem("token"));
			}
		};

		loadData();
	}, []);

	return (
		<StoreContext.Provider value={contextValue}>
			{props.children}
		</StoreContext.Provider>
	);
};
