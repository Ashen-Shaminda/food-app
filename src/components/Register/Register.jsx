import React, { useState } from "react";
import "./Register.css";
import { Link, useNavigate } from "react-router-dom";
import { registerUser } from "../../services/userServices";
import { toast } from "react-toastify";

const Register = () => {
	const navigate = useNavigate();
	const [data, setData] = useState({
		name: "",
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

		const response = await registerUser(data);

		try {
			if (response.status === 201) {
				toast.success("Registered Successfully.");
				navigate("/login");
			} else {
				toast.error("Unable to register. Try again.");
			}
		} catch {
			toast.error("Unable to register. Try again.");
		}
	};

	return (
		<div className="login-container">
			<div className="d-flex align-items-center">
				<div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
					<div className="card border-0 shadow rounded-3 my-5">
						<div className="card-body p-4 p-sm-5">
							<h5 className="card-title text-center mb-5 fw-light fs-5">
								Sign up
							</h5>
							<form onSubmit={onSubmitHandler}>
								<div className="form-floating mb-3">
									<input
										type="text"
										className="form-control"
										id="floatingName"
										placeholder="Full Name"
										name="name"
										onChange={onChangeHandler}
										value={data.name}
										required
									/>
									<label htmlFor="floatingName">Full Name</label>
								</div>
								<div className="form-floating mb-3">
									<input
										type="email"
										className="form-control"
										id="floatingInput"
										placeholder="name@example.com"
										name="email"
										onChange={onChangeHandler}
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
										name="password"
										onChange={onChangeHandler}
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
										Sign up
									</button>
								</div>
								<div className="mt-4">
									Already have an account ?{" "}
									<Link to="/login">Sign in</Link>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Register;
