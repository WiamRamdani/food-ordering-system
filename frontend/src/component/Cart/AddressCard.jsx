import React from 'react'
import HomeIcon from '@mui/icons-material/Home';
import { Button, Card } from '@mui/material';

export const AddressCard = ({item,showButton, handleSelectAddress}) => {
    return (
        <Card className="flex  gap-5 w-65  p-5" >
            <HomeIcon/>
            <div className='space-y-3 text-gray-500'>
                <h1 className='font-semibold text-lg text-white'>Home</h1>
                <p>
                    123 Maplewood Avenue ,Springfield, IL 62704 ,United States
                </p>
                {showButton && (
                    <Button variant="outlined" fullWidth onClick={()=>handleSelectAddress(item)}>select </Button>
                )}
            </div>
        </Card>
    )
}
