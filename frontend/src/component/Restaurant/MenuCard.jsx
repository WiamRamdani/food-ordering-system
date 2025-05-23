import React from 'react'
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import { Button, Checkbox, FormControlLabel, FormGroup, Typography } from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

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
const MenuCard = () => {
    const handleCheckBoxChange=(value)=>{
        console.log("value")
    }
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
                    src="https://images.unsplash.com/photo-1568901346375-23c9450c58cd?
                    w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWF
                    yY2h8Mnx8YnVyZ2VyfGVufDB8MHwwfHx8MA%3D%3D"
                    alt=""/>
                    <div className='space-y-1 lg:space-y-5 lg:max-w-2xl'>
                        <p className='font-semibold text-xl'>Burger</p>
                        <p>60 MAD</p>
                        <p className='text-gray-400'>nice food</p>

                    </div>
                </div>
            </div>
        </AccordionSummary>
        <AccordionDetails>
            <form>
                <div className='flex gap-5 flex-wrap'>
                    {ingredients.map((item)=>
                        <div>
                            <p>{item.category}</p>
                            <FormGroup>
                                {item.ingredients.map((item)=>(
                                <FormControlLabel control={<Checkbox onChange={()=>
                                    handleCheckBoxChange()}/>} label={item} />
                            ))}
                            </FormGroup>
                        </div>
                    )}
                </div>
                <div className='pt-5'> 
                    <Button variant='contained' disabled={false} type="submit">{true?"Add to Cart":"Out of Stock"}</Button>
                </div>
            </form> 
        </AccordionDetails>
      </Accordion>
    )
}

export default MenuCard