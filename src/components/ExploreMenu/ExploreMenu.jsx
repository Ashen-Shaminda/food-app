import React from "react";
import { menuItems } from "../../assets/assets";
import "./ExploreMenu.css";
import { useRef } from "react";

const ExploreMenu = ({ category, setCategory }) => {
	const menuRef = useRef(null);

	const scrollLeft = () => {
		if (menuRef.current) {
			return menuRef.current.scrollBy({
				left: -200,
				behavior: "smooth",
			});
		}
	};

	const scrollRight = () => {
		if (menuRef.current) {
			return menuRef.current.scrollBy({
				left: 200,
				behavior: "smooth",
			});
		}
	};

	return (
		<div className="container explore-menu position-relative">
			<h1 className="d-flex align-items-center justify-content-between">
				Explore Our Menu
				<div className="d-flex gap-2">
					<i
						className="bi bi-arrow-left-circle scroll-icon"
						onClick={scrollLeft}
					></i>
					<i
						className="bi bi-arrow-right-circle scroll-icon"
						onClick={scrollRight}
					></i>
				</div>
			</h1>
			<p>Explore lists of dishes from top categories</p>
			<div
				className="d-flex justify-content-between gap-4 overflow-auto explore-menu-list"
				ref={menuRef}
			>
				{menuItems.map((item, index) => (
					<div
						key={index}
						className="text-center explore-menu-list-item"
						onClick={() =>
							setCategory((prev) =>
								prev === item.category ? "All" : item.category
							)
						}
					>
						<img
							src={item.icon}
							alt="food"
							height={128}
							width={128}
							className={
								item.category === category
									? "rounded-circle active"
									: "rounded-circle"
							}
						/>
						<p className="mt-2 fw-bold">{item.category}</p>
					</div>
				))}
			</div>
			<hr />
		</div>
	);
};

export default ExploreMenu;
