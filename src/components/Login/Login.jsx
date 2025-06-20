import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { loginUser } from "../../services/authServices";
import { StoreContext } from "../../context/StoreContext";
import "./Login.css";

const Login = () => {
	const { setToken, loadCartData } = useContext(StoreContext);
	const navigate = useNavigate();
	const [data, setData] = useState({
		email: "",
		password: "",
	});

	const onChangeHandler = (e) => {
		const name = e.target.name;
		const value = e.target.value;

		setData((prev) => ({ ...prev, [name]: value }));
	};

	const onSubmitHandler = async (e) => {
		e.preventDefault();

		const response = await loginUser(data);

		try {
			if (response.status === 200) {
				setToken(response.data.token);
				localStorage.setItem("token", response.data.token);
				await loadCartData(response.data.token);
				navigate("/");
			} else {
				toast.error("Unable to login. Try again.");
			}
		} catch {
			toast.error("Unable to login. Try again.");
		}
	};

	return (
		<div className="login-container">
			<div className="d-flex align-items-center">
				<div className=" col-sm-9 col-md-7 col-lg-5 mx-auto">
					<div className="card border-0 shadow rounded-3 my-5">
						<div className="card-body p-4 p-sm-5 ">
							<h5 className="card-title text-center mb-5 fw-light fs-5">
								Sign in
							</h5>
							<form onSubmit={onSubmitHandler}>
								<div className="form-floating mb-3">
									<input
										type="email"
										className="form-control"
										id="floatingInput"
										placeholder="name@example.com"
										onChange={onChangeHandler}
										name="email"
										value={data.email}
										required
									/>
									<label htmlFor="floatingInput">Email address</label>
								</div>
								<div className="form-floating mb-3">
									<input
										type="password"
										className="form-control"
										id="floatingPassword"
										placeholder="Password"
										onChange={onChangeHandler}
										name="password"
										value={data.password}
										required
									/>
									<label htmlFor="floatingPassword">Password</label>
								</div>
								<div className="d-grid">
									<button
										className="btn btn-primary btn-login text-uppercase fw-bold"
										type="submit"
									>
										Sign in
									</button>
								</div>
								<div className="mt-4">
									Create an account ?{" "}
									<Link to="/register">Sign up</Link>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Login;
