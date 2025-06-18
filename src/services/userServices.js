import axios from "axios";

const API_URL = "http://localhost:8080/api";

const registerUser = async (data) => {
	try {
		const response = await axios.post(`${API_URL}/register`, data);

		return response;
	} catch (error) {
		throw new Error("There's an error.", error);
	}
};

export { registerUser };
