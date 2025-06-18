import axios from "axios";

const API_URL = "http://localhost:8080/api";

const registerUser = async (data) => {
	try {
		const response = await axios.post(`${API_URL}/register`, data);

		return response;
	} catch (error) {
		throw new Error("Error occured while registering the user.", error);
	}
};

const loginUser = async (data) => {
	try {
		const response = await axios.post(`${API_URL}/login`, data);

		return response;
	} catch (error) {
		throw new Error("Error occured while logging the user.", error);
	}
};

export { registerUser, loginUser };
