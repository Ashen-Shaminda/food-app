import { useMemo } from "react";

export const useCalculateCartTotal = (foodList, quantities) => {
	return useMemo(() => {
		const cartItems = foodList.filter((food) => quantities[food.id] > 0);

		const subtotal = cartItems.reduce(
			(acc, food) => acc + food.price * quantities[food.id],
			0
		);

		const shippingFee = subtotal === 0 ? 0.0 : 10;
		const tax = subtotal * 0.1;
		const total = subtotal + shippingFee + tax;

		return {
			cartItems,
			subtotal,
			shippingFee,
			tax,
			total,
		};
	}, [foodList, quantities]);
};
