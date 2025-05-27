import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Stack, Typography } from '@mui/material'
import React, { use, useEffect, useState } from 'react'

import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import MenuCard from './MenuCard';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import { getRestaurantById, getRestaurantsCategory } from '../State/Restaurant/Action';

import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import { getMenuItemsByRestaurantId } from '../State/Menu/Action';
const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: (theme.vars ?? theme).palette.text.secondary,
  ...theme.applyStyles('dark', {
    backgroundColor: '#1A2027',
  }),
}));


const foodTypes=[
    {label:"All",value:"all"},
    {label:"Vegetarian only",value:"vegetarian"},
    {label:"Seasonal",value:"seasonal"}
]
// const menu =[1,1,1,1,1,1]
const RestaurantDetails = () => {
        const [foodType, setFoodType]=useState("")
        const navigate=useNavigate();
        const dispatch = useDispatch();
        const jwt = localStorage.getItem("jwt");

        const {auth,menu,restaurant}=useSelector(store=>store);

        const [selectedCategory, setSelectedCategory] = useState("");

        const {id}=useParams();

        const handleFilter=(e)=>{
            setFoodType(e.target.value)
            console.log(e.target.value, e.target.name)
        }

        const handleFilterCategory=(e,value)=>{
            setSelectedCategory(e.target.value)
            console.log(e.target.value, e.target.name)
        } 

        console.log("restaurant", restaurant)

        useEffect(()=>{
            dispatch(getRestaurantById({jwt, restaurantId : id}))
            dispatch(getRestaurantsCategory({jwt,restaurantId : id}))
        },[])

        useEffect(()=>{
            dispatch(getMenuItemsByRestaurantId(
                {jwt, 
                restaurantId : id,
                vegetarian:foodType==="vegetarian" ,
                seasonal:foodType==="seasonal", 
                foodCategory: selectedCategory}
            ));
        },[selectedCategory,foodType])
    return (
        <div className='px-5 lg:px-20'>

            <section>
                <h3 className='text-gray-500 py-2 mt-10'>Home/india/indian fast food/3</h3>
                <div>
                    
                    <Grid container spacing={2}>
                        <Grid size={4}>
                            <Stack spacing={2}>
                                <Item sx={{ height: '50%'}}><img className='w-full h-[40vh] object-cover' 
                                        src={restaurant.restaurant?.images[0]}
                                        alt=""/></Item>
                                <Item x={{ height: '50%'}}><img className='w-full h-[40vh] object-cover' 
                                        src={restaurant.restaurant?.images[1]}
                                        alt=""/></Item>
                            </Stack>
                        </Grid>
                        <Grid size={8}>
                            <Item sx={{ height: '100%', boxSizing: 'border-box' }}><img className='w-full h-[80vh] object-cover' 
                                src={restaurant.restaurant.images[2]}
                                alt=""/></Item>
                        </Grid>
      </Grid>
                </div>
                
                <div className='space-y-3 mt-3'>
                <h1 className='text-4xl font-semibold'>{restaurant.restaurant?.nom} </h1>
                     <p className='text-gray-500 mt-1'>
                        {restaurant.restaurant?.description}
                    </p>
                    <p className='text-gray-500 flex items-center gap-3'>
                        <LocationOnIcon/>
                        <span>
                            Fez, Morocco
                        </span>
                    </p>
                    <p className='text-orange-400 flex items-center gap-3'>
                        <CalendarTodayIcon/>
                        <span>
                            Mon-Sun: 9:00 AM - 9:00PM (Today)
                        </span>
                    </p>
                </div>
                
            </section>
            <Divider/>
            <section className='pt-[2rem] lg:flex relative'>
                <div className='space-y-10 lg:w-[20%] filter p-5 shadow-md'>
                    <div className='box space-y-5 lg:sticky top-28 d'>
                        <div>
                            <Typography variant="h5" sx={{paddingBottom:"1rem"}}>
                                Food Type
                            </Typography>
                            <FormControl className='py-10 space-y-5' component={"fieldset"}>
                                <RadioGroup onChange={handleFilter} name='food_type' value={foodType}>
                                    {foodTypes.map((item)=>(
                                        <FormControlLabel 
                                        key={item.value}
                                         value={item.value} 
                                         control={<Radio />} 
                                         label={item.label} 
                                        />
                                    ))}
                                </RadioGroup>
                            </FormControl>
                        </div>
                        <Divider/>
                        <div>
                            <Typography variant="h5" sx={{paddingBottom:"1rem"}}>
                                Food Category
                            </Typography>
                            
                            <FormControl className='py-10 space-y-5' component={"fieldset"}>
                                
                                { <RadioGroup onChange={handleFilterCategory} name='food_category' value={selectedCategory} > 
                                    {restaurant.restaurant?.categories?.map((item)=>(
                                        <FormControlLabel 
                                        //  key={item}
                                         value={item.nom} 
                                         control={
                                            <Radio
                                                onClick={() => {
                                                    if (selectedCategory === item.nom) {
                                                        setSelectedCategory(""); // ← désélectionne
                                                    }
                                                }}
                                            />
                                        }
                                         label={item.nom} 
                                        />
                                    ))}
                                </RadioGroup>}
                            </FormControl>
                        </div>
                    </div>
                </div>

                <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                    { menu.menuItems?.map((item)=><MenuCard item={item} restaurantName={restaurant.restaurant?.nom}/>)}
                </div>
            </section>
            
        </div>
    );
}
export default RestaurantDetails;