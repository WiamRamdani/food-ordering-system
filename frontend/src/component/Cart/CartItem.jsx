import { Chip, IconButton } from '@mui/material'
import React from 'react'
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

export const CartItem = () => {
    return (
        <div className='px-5'>
            <div className='lg:flex items-center lg:space-x-5'>
                <div>
                    <img
                        className='w-[5rem] h-[5rem] object-cover' 
                        src="https://images.unsplash.com/photo-1568901346375-23c9450c58cd?
                        w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWF
                        yY2h8Mnx8YnVyZ2VyfGVufDB8MHwwfHx8MA%3D%3D" 
                        alt=""
                    />
                </div>
                <div className='flex items-center justify-between lg:w-[70%]'>
                    <div className='space-y-1 lg:space-y-3 w-full'>
                        <p> Burger</p>
                        <div className='flex justify-between items-center'>
                            <div className='flex items-center space-x-1'>
<IconButton>
    <RemoveCircleOutlineIcon/>
</IconButton> 
<div className='w-5 h-5 text-xs flex items-center justify-center'>
    {5}
</div>    
<IconButton>
    <AddCircleOutlineIcon/>
</IconButton>                            
                            </div>
                        </div>

                    </div>
                    <p> 60.0 MAD</p>
                </div>
            </div>
            <div className='pt-3 space-x-2'>

                {[1,1,1,].map((item)=><Chip label={"bread"}/>)}

            </div>

        </div>
    )
}