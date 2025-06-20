import axios from "axios";

const API_URL = "http://localhost:8080/api/cart";

const addQuantity = async (foodId, token) => {
	try {
		await axios.post(
			API_URL,
			{ foodId },
			{
				headers: { Authorization: `Bearer ${token}` },
			}
		);
	} catch (error) {
		throw new Error("Failed to add item to the cart: " + error.message);
	}
};

const removeQuantity = async (foodId, token) => {
	try {
		await axios.post(
			`${API_URL}/remove`,
			{ foodId },
			{
				headers: { Authorization: `Bearer ${token}` },
			}
		);
	} catch (error) {
		throw new Error("Failed to remove item from the cart: " + error.message);
	}
};

const getCartData = async (token) => {
	try {
		const response = await axios.get(API_URL, {
			headers: { Authorization: `Bearer ${token}` },
		});

		return response;
	} catch (error) {
		throw new Error("Failed to get cart data: " + error.message);
	}
};

export { addQuantity, removeQuantity, getCartData };
