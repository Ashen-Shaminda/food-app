import axios from "axios";

const API_URL = "http://localhost:8080/api/foods";

const fetchFoodList = async () => {
	try {
		const response = await axios.get(API_URL);

		return response.data;
	} catch (error) {
		throw new Error("Failed to fetch food list: " + error.message);
	}
};

const fetchFoodDetails = async (id) => {
	try {
		const response = await axios.get(`${API_URL}/` + id);

		return response.data;
	} catch (error) {
		throw new Error("Error fetching food details: " + error.message);
	}
};

export { fetchFoodList, fetchFoodDetails };
