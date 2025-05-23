import { Button, Card } from '@mui/material'
import React from 'react'

export const OrderCard = () => {
  return (
    <Card className='flex justify-between items-end p-4'>
        <div className='flex items-center space-x-5'>
            <img 
                className="h-16 w-16"
                src="https://images.unsplash.com/photo-1568901346375-23c9450c58cd?
                w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWF
                yY2h8Mnx8YnVyZ2VyfGVufDB8MHwwfHx8MA%3D%3D"
                alt=""
            />
            <div>
                <p>Burger</p>
                <p>60.0 MAD</p>
            </div>
        </div>
        <div>
            <Button className="cursor-not-allowed"> completed </Button>
        </div>
    </Card>
  )
}
