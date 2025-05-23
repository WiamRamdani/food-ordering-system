import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from '@mui/material'
import React, { useState } from 'react'

import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import MenuCard from './MenuCard';


const categories=[
    "pizza",
    "burger",
    "chicken",
    "pasta",
    "rice"
]
const foodTypes=[
    {label:"All",value:"all"},
    {label:"Vegetarian only",value:"vegetarian"},
    {label:"Non-Vegetarian",value:"non_vegetarian"},
    {label:"Seasonal",value:"seasonal"}
]
const menu =[1,1,1,1,1,1]
const RestaurantDetails = () => {
        const [foodType, setFoodType]=useState("all")
        const handleFilter=(e)=>{
            console.log(e.target.value, e.target.name)
        }
    return (
        <div className='px-5 lg:px-20'>

            <section>
                <h3 className='text-gray-500 py-2 mt-10'>Home/india/indian fast food/3</h3>
                <div>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>

                            <img className='w-full h-[40vh] object-cover' 
                            src="https://plus.unsplash.com/premium_photo-1661953124283-76d0a8436b87?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmVzdGF1cmFudHxlbnwwfDB8MHx8fDA%3D" 
                            alt=""/>
                        </Grid>
                        <Grid item xs={12} lg={6}>

                            <img className='w-full h-[40vh] object-cover' 
                            src="https://images.pexels.com/photos/1449775/pexels-photo-1449775.jpeg?auto=compress&cs=tinysrgb&w=600" 
                            alt=""/>
                        </Grid>
                        <Grid item xs={12} lg={6}>

                            <img className='w-full h-[35vh] object-cover' 
                            src="https://images.pexels.com/photos/6267/menu-restaurant-vintage-table.jpg?auto=compress&cs=tinysrgb&w=600" 
                            alt=""/>
                        </Grid>
                    </Grid>
                </div>
                
                <div className='space-y-3 mt-3'>
                <h1 className='text-4xl font-semibold'> Indian Fast food</h1>
                     <p className='text-gray-500 mt-1'>
                        Step into our Indian restaurant and embark on a culinary journey through the rich 
                        and diverse flavors of India. From fragrant curries and sizzling tandoori dishes to 
                        freshly baked naan and delicate sweets, every plate is a celebration of tradition and 
                        taste. Whether you're craving the bold spices of the North or the vibrant coastal recipes 
                        of the South, our chefs craft each dish with passion and authenticity. Join us for a warm, 
                        inviting atmosphere and a truly unforgettable dining experience.
                        
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
                                <RadioGroup onChange={handleFilter} name='food_type' value={foodType }>
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
                                <RadioGroup onChange={handleFilter} name='food_category' value={foodType }>
                                    {categories.map((item)=>(
                                        <FormControlLabel 
                                        key={item}
                                         value={item} 
                                         control={<Radio />} 
                                         label={item} 
                                        />
                                    ))}
                                </RadioGroup>
                            </FormControl>
                        </div>
                    </div>
                </div>

                <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                    {menu.map((item)=><MenuCard/>)}
                </div>
            </section>
            
        </div>
    );
}
export default RestaurantDetails;