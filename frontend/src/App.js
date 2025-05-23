import { CssBaseline, ThemeProvider } from '@mui/material';
import './App.css';
import { Navbar } from './component/Navbar/Navbar';
import { darkTheme } from './Theme/DarkTheme';
import { Home } from './component/Home/Home';
import { Cart } from './component/Cart/Cart';
import { Profile } from './component/Profile/Profile';
import RestaurantDetails from './component/Restaurant/RestaurantDetails';
import CustomerRouter from './Routers/CustomerRouter';
import { useEffect } from 'react';
import { getUser } from './component/State/Authentication/Action';
import { useDispatch, useSelector } from 'react-redux';
import { store } from './component/State/Authentication/Store';

function App() {
  const dispatch = useDispatch()
  const jwt = localStorage.getItem("jwt")
  const {auth}=useSelector(store=>store)
  useEffect(()=>{
    dispatch(getUser(auth.jwt || jwt ))
  },[auth.jwt])
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline/>
      <CustomerRouter/>
      
    </ThemeProvider>
  );
}

export default App;
