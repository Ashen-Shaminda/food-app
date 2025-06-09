import React from "react";
import { assets } from "../../assets/assets";
import "./MenuBar.css";
import { Link } from "react-router-dom";

const MenuBar = () => {
	return (
		<nav className="navbar navbar-expand-lg bg-body-tertiary">
			<div className="container">
				<img
					src={assets.icon}
					height={48}
					width={48}
					className="mx-2 me-lg-3 "
				/>
				<button
					className="navbar-toggler"
					type="button"
					data-bs-toggle="collapse"
					data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent"
					aria-expanded="false"
					aria-label="Toggle navigation"
				>
					<span className="navbar-toggler-icon"></span>
				</button>
				<div
					className="collapse navbar-collapse"
					id="navbarSupportedContent"
				>
					<ul className="navbar-nav me-auto mb-2 mb-lg-0">
						<li className="nav-item">
							<Link className="nav-link" aria-current="page" to="/">
								Home
							</Link>
						</li>
						<li className="nav-item">
							<Link className="nav-link" to="/explore">
								Explore
							</Link>
						</li>
						<li className="nav-item">
							<Link className="nav-link" to="/contact-us">
								Contact Us
							</Link>
						</li>
					</ul>
					{/* <form className="d-flex" role="search">
						<input
							className="form-control me-2"
							type="search"
							placeholder="Search"
							aria-label="Search"
						/>
						<button className="btn btn-outline-success" type="submit">
							Search
						</button>
					</form> */}
					<div className="d-flex align-items-center gap-4">
						<div className="position-relative">
							<img
								src={assets.cart}
								height={32}
								width={32}
								className="position-relative"
							/>
							<span className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
								0
								<span className="visually-hidden">unread messages</span>
							</span>
						</div>
						<button className="btn btn-outline-primary" type="button">
							Login
						</button>
						<button className="btn btn-outline-success" type="button">
							Register
						</button>
					</div>
				</div>
			</div>
		</nav>
	);
};

export default MenuBar;
