import React, { useState } from 'react'
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import { Button, Checkbox, FormControlLabel, FormGroup, Typography } from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { categorizeIngredients } from '../util/categorizeIngredients';
import { addItemToCart } from '../State/Cart/Action';
import { useDispatch } from 'react-redux';

const ingredients=[
    {
        category:"Nuts & Seeds",
        ingredients:["cashews"]
    },
    {
        category:"Protein",
        ingredients:["Ground beef","Bacon strips"]
    },
    {
        category:"Bread",
        ingredients:["Hamburger buns"]
    },
    {
        category:"Vegetable",
        ingredients:["Lettuce","Tomato slices","Pickles","Onion slices"]
    },
    {
        category:"Condiment",
        ingredients:["Ketchup"]
    }
]
const MenuCard = ({item,restaurantName}) => {
    const dispatch = useDispatch();
    const [selectedIngredients, setSelectedIngredients] =useState([]);
    const handleCheckBoxChange=(itemName)=>{
        console.log("value",itemName)
        if(selectedIngredients.includes(itemName)) {
            setSelectedIngredients(selectedIngredients.filter(item => item !== itemName));
        }else{
            setSelectedIngredients([...selectedIngredients, itemName]);
        }
    };
    const handleAddItemToCart=(e)=>{
        e.preventDefault();
        const reqData={
           jwt: localStorage.getItem("jwt"),
           cartItem:{
                nom_plat:item.nom,
                nom_restaurant:restaurantName,
                prixTotal:item.prix,
                quantity:1,
                ingredients:selectedIngredients,
            },
        };
        console.log("reqData",reqData);
        dispatch(addItemToCart(reqData));
        
        
    }

    const groupedIngredients = categorizeIngredients(item.ingredients || []);
    
    return (
        <Accordion >
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
            <div className='lg:flex items-center justify-between'>
                <div className='lg:flex items-center lg:gap-5'>
                    <img className='w-[7rem] h-[7rem] object-cover' 
                    src={item.image[0]}
                    alt=""/>
                    <div className='space-y-1 lg:space-y-5 lg:max-w-2xl'>
                        <p className='font-semibold text-xl'>{item.nom}</p>
                        <p>{item.prix} MAD</p>
                        <p className='text-gray-400'>{item.description}</p>

                    </div>
                </div>
            </div>
        </AccordionSummary>
        <AccordionDetails>
            <form onSubmit={handleAddItemToCart}>
                <div className='flex gap-5 flex-wrap'>
                    {Object.keys(groupedIngredients).map((category)=>
                        <div>
                            <p>{category}</p>
                            <FormGroup>
                                {groupedIngredients[category].map((item)=>(
                                    <FormControlLabel 
                                        key={item.id} 
                                        control={
                                            <Checkbox onChange={()=>
                                                handleCheckBoxChange(item.nom)
                                            }/>
                                        } 
                                        label={item.nom} 
                                    />
                            ))}
                            </FormGroup>
                        </div>
                    )}
                </div>
                <div className='pt-5'> 
                    <Button  variant='contained' disabled={false} type="submit">
                        {true?"Add to Cart":"Out of Stock"}
                    </Button>
                </div>
            </form> 
        </AccordionDetails>
      </Accordion>
    )
}

export default MenuCard