import { Divider, Modal, Box, TextField, Grid } from '@mui/material'
import React from 'react'
import { CartItem } from './CartItem'
import { AddressCard } from './AddressCard'
import { Button, Card } from '@mui/material';
import AddLocationAltIcon from '@mui/icons-material/AddLocationAlt';
import * as Yup from "yup"
import { Formik, Field, ErrorMessage, Form } from 'formik';
import { useSelector } from 'react-redux';


export const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    outline: 'none',
    boxShadow: 24,
    p: 4,
};

const initialValues={
    streetAddress:"",
    state:"",
    pincode:"",
    city:""
}

const validationSchema=Yup.object({
    streetAddress:Yup.string().required("Street Address is required"),
    state:Yup.string().required("State is required"),
    pincode:Yup.number().integer().required("Pincode  is required"),
    city:Yup.string().required("City is required")
})

const items=[1,1]
const Cart = () => {
    const {cart}=useSelector(store=>store)
    const createOrderUsingSelectedAddress=()=>{}
    const handleOpenAddressModel=()=>setOpen(true)
    const [open, setOpen] = React.useState(false);
    const handleClose = () => setOpen(false);
    const handleSubmit=(values)=>{
        console.log("form value ", values)
    }
    return (
    <>
        <main className='lg:flex justify-between'>
                <section className='lg:w-[30%]   space-y-6 lg:min-h-screen pt-10 '>
                    {cart?.cart?.commandeItems?.map((item)=>(
                        <CartItem item={item}/>
                    ))}
                    <Divider/>
                
                    <div className='BillDetails px-5 text-sm'>
                        <p className='font-extralight py-5'>Bill Details </p>
                        <div className='space-y-3'>
                            <div className='flex justify-between text-gray-400'>
                                <p>Item Total</p>
                                <p>120.0 MAD</p>
                            </div>
                            <div className='flex justify-between text-gray-400'>
                                <p>Deliver Fee</p>
                                <p>30.0 MAD</p>
                            </div>
                            <Divider/>
                            <div className='flex justify-between text-gray-400'>
                                <p>Total Pay</p>
                                <p>150.0 MAD</p>
                            </div>
                        </div>
                    </div>
                </section>
                <Divider orientation='vertical' flexItem />
                <section className='lg:w-[70%]   flex justify-center px-5 pb-10 lg:pb-0 '>
                    <div>
                        <h1 className='text-center font-semibold text-2xl py-10'>
                            Choose Delivery Address
                        </h1>
                        <div className='grid grid-cols-3 gap-5 justify-items-center'>
                            {[1,1,1,1,1].map((item)=>(
                                <AddressCard handleSelectAddress={createOrderUsingSelectedAddress} item={item} showButton={true}/>
                            ))}
                            <Card className="flex gap-5 w-70  p-5">
                                <AddLocationAltIcon/>
                                <div className='space-y-3 text-gray-500'>
                                    <h1 className='font-semibold text-lg text-white'>Add New Address</h1>
                                    {
                                        <Button variant="outlined" fullWidth onClick={handleOpenAddressModel}>Add </Button>
                                    }
                                </div>
                            </Card>
                        </div>
                    </div>
                </section>
        </main>
        <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
            <Formik initialValues={initialValues}
            validationSchema={validationSchema}
            onSubmit={handleSubmit}>

                <Form>
                    <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Field
                        as={TextField}
                        name="streetAddress"
                        label="Street Address"
                        fullWidth
                        // variant="outlined"
                        // error={!ErrorMessage("streetAddress")}
                        // helperText={
                        //     <ErrorMessage>
                        //         {(msg)=><span className='text-red-600'>{msg}</span>}
                        //     </ErrorMessage>
                        // }
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Field
                        as={TextField}
                        name="state"
                        label="State"
                        fullWidth
                        // variant="outlined"
                        // error={!ErrorMessage("streetAddress")}
                        // helperText={
                        //     <ErrorMessage>
                        //         {(msg)=><span className='text-red-600'>{msg}</span>}
                        //     </ErrorMessage>
                        // }
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Field
                        as={TextField}
                        name="pincode"
                        label="PinCode"
                        fullWidth
                        // variant="outlined"
                        // error={!ErrorMessage("streetAddress")}
                        // helperText={
                        //     <ErrorMessage>
                        //         {(msg)=><span className='text-red-600'>{msg}</span>}
                        //     </ErrorMessage>
                        // }
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Field
                        as={TextField}
                        name="city"
                        label="City"
                        fullWidth
                        // variant="outlined"
                        // error={!ErrorMessage("streetAddress")}
                        // helperText={
                        //     <ErrorMessage>
                        //         {(msg)=><span className='text-red-600'>{msg}</span>}
                        //     </ErrorMessage>
                        // }
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button fullWidth variant='contained' type="submit" color="primary">
                            Deliver Here
                        </Button>
                    </Grid>
                    </Grid>   
                </Form>
                
            </Formik>
        </Box>
      </Modal>
    </>
    )
}
export default Cart;