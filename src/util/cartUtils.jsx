const calculateCartTotal = (cartItems, quantities) => {
	const subtotal = cartItems.reduce(
		(acc, food) => acc + food.price * quantities[food.id],
		0
	);

	const shippingFee = subtotal === 0 ? 0.0 : 10;
	const tax = subtotal * 0.1;
	const total = subtotal + shippingFee + tax;

	return {
		subtotal,
		shippingFee,
		tax,
		total,
	};
};

export { calculateCartTotal };
