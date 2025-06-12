import React, { useContext } from "react";
import "./PlaceOrder.css";
import { assets } from "../../assets/assets";
import { StoreContext } from "../../context/StoreContext";
import { calculateCartTotal } from "../../util/cartUtils";

const PlaceOrder = () => {
	const { foodList, quantities } = useContext(StoreContext);
	const cartItems = foodList.filter((food) => quantities[food.id] > 0);
	const { subtotal, shippingFee, tax, total } = calculateCartTotal(
		cartItems,
		quantities
	);

	return (
		<div className="container my-4">
			<main>
				<div className="py-3 text-center">
					<img
						className="d-block mx-auto"
						src={assets.icon}
						alt=""
						width="98"
						height="98"
					/>
				</div>
				<hr />
				<div className="row g-5">
					<div className="col-md-5 col-lg-4 order-md-last">
						<h4 className="d-flex justify-content-between align-items-center mb-3">
							<span className="text-primary">Your cart</span>
							<span className="badge bg-primary rounded-pill">
								{cartItems.length}
							</span>
						</h4>
						<ul className="list-group mb-3">
							{cartItems.map((item) => (
								<li
									className="list-group-item d-flex justify-content-between lh-sm"
									key={item.id}
								>
									<div>
										<h6 className="my-0">{item.name}</h6>
										<small className="text-body-secondary">
											Qty: {quantities[item.id]}
										</small>
									</div>
									<span className="text-body-secondary">
										&#36;{item.price * quantities[item.id]}
									</span>
								</li>
							))}
							<li className="list-group-item d-flex justify-content-between lh-sm">
								<div>
									<span>Shipping</span>
								</div>
								<span className="text-body-secondary">
									{subtotal === 0 ? 0.0 : shippingFee.toFixed(2)}
								</span>
							</li>
							<li className="list-group-item d-flex justify-content-between lh-sm">
								<div>
									<span className="text-body-secondary">
										Tax (10%)
									</span>
								</div>
								<span className="text-body-secondary">
									&#36;{tax.toFixed(2)}
								</span>
							</li>

							<li className="list-group-item d-flex justify-content-between">
								<span>Total (USD)</span>{" "}
								<strong>&#36;{total.toFixed(2)}</strong>
							</li>
						</ul>
					</div>
					<div className="col-md-7 col-lg-8">
						<h4 className="mb-3">Billing address</h4>
						<form className="needs-validation" noValidate>
							<div className="row g-3">
								<div className="col-sm-6">
									<label htmlFor="firstName" className="form-label">
										First name
									</label>
									<input
										type="text"
										className="form-control"
										id="firstName"
										placeholder=""
										required
									/>
								</div>
								<div className="col-sm-6">
									<label htmlFor="lastName" className="form-label">
										Last name
									</label>
									<input
										type="text"
										className="form-control"
										id="lastName"
										placeholder=""
										required
									/>
								</div>
								<div className="col-12">
									<label htmlFor="email" className="form-label">
										Email
									</label>
									<div className="input-group has-validation">
										<span className="input-group-text">@</span>
										<input
											type="email"
											className="form-control"
											id="email"
											placeholder="Email"
											required
										/>
									</div>
								</div>
								<div className="col-12">
									<label htmlFor="address" className="form-label">
										Address
									</label>
									<input
										type="text"
										className="form-control"
										id="address"
										placeholder="1234 Main St"
										required
									/>
								</div>
								<div className="col-12">
									<label htmlFor="phone" className="form-label">
										Phone Number
									</label>
									<input
										type="number"
										className="form-control"
										id="phone"
										placeholder="Phone Number"
										required
									/>
								</div>
								<div className="col-md-5">
									<label htmlFor="country" className="form-label">
										Country
									</label>
									<select
										className="form-select"
										id="country"
										required
									>
										<option>Choose...</option>
										<option>United States</option>
									</select>
								</div>
								<div className="col-md-4">
									<label htmlFor="state" className="form-label">
										State
									</label>
									<select className="form-select" id="state" required>
										<option>Choose...</option>
										<option>California</option>
									</select>
								</div>
								<div className="col-md-3">
									<label htmlFor="zip" className="form-label">
										Zip
									</label>
									<input
										type="number"
										className="form-control"
										id="zip"
										placeholder="Zip Code"
										required
									/>
								</div>
							</div>

							<hr className="my-4" />

							<button
								className="w-100 btn btn-primary btn-lg"
								disabled={cartItems.length === 0}
							>
								Continue to checkout
							</button>
						</form>
					</div>
				</div>
			</main>
		</div>
	);
};

export default PlaceOrder;
