import { Route, Routes } from "react-router-dom";
import "./App.css";
import MenuBar from "./components/Menubar/MenuBar";
import Home from "./pages/Home/Home";
import ContactUs from "./pages/ContactUs/ContactUs";
import Explore from "./pages/Explore/Explore";
import FoodDetails from "./pages/FoodDetails/FoodDetails";
import NotFound from "./pages/NotFound/NotFound";
import Cart from "./pages/Cart/Cart";
import PlaceOrder from "./pages/PlaceOrder/PlaceOrder";

function App() {
	return (
		<div>
			<MenuBar />
			<Routes>
				<Route path="/" element={<Home />} />
				<Route path="/explore" element={<Explore />} />
				<Route path="/contact-us" element={<ContactUs />} />
				<Route path="/food/:id" element={<FoodDetails />} />
				<Route path="/cart" element={<Cart />} />
				<Route path="/order" element={<PlaceOrder />} />
				<Route path="*" element={<NotFound />} />
			</Routes>
		</div>
	);
}

export default App;
