import { Button, MenuItem, Select, TextField, Typography } from '@mui/material';
import { Form, Field, Formik } from 'formik';
import React from 'react'
import { useNavigate } from 'react-router-dom';
import { registerUser } from '../State/Authentication/Action';
import { useDispatch } from 'react-redux';

const initialValues={
  fullname:"",
  email:"",
  password:"",
  role:"CUSTOMER"
}
export default function RegisterForm ()  {
  const navigate=useNavigate();
  const dispatch = useDispatch()
  const handleSubmit = (values) => {
    console.log("form values", values)
    dispatch(registerUser({userData:values, navigate}))
  };
  return (
      <div>
        <Typography variant='h5' className='text-center'>
          Register
        </Typography>
  
        <Formik onSubmit={handleSubmit} initialValues={initialValues}>
          <Form>
            <Field
              as={TextField}
              name="fullname"
              label="full name"
              fullWidth
              variant="outlined"
              margin="normal"
            />
            <Field
              as={TextField}
              name="email"
              label="email"
              fullWidth
              variant="outlined"
              margin="normal"
            />
            <Field
              as={TextField}
              name="password"
              label="password"
              fullWidth
              variant="outlined"
              margin="normal"
              type="password"
            />
            <Field
              fullWidth
              margin="normal"
              as={Select}
              labelId="role-simple-select-label"
              id="role-simple-select"
              name="role"
            >
              <MenuItem value={"CUSTOMER"}>Customer</MenuItem>
              <MenuItem value={"RESTAURANT_OWNER"}>Restaurant Owner</MenuItem>
              <MenuItem value={"ADMIN"}>Admin</MenuItem>
            </Field>
            <Button sx={{mt:2, padding:"1rem"}} className='mt-5' fullWidth type="submit" variant="contained">Register</Button>
          </Form>
        </Formik>
        <Typography variant='body2' align="center" sx={{mt:3}}> 
          You already have an account ?
          <Button size='small' onClick={()=>navigate("/account/login")}>
            LOGIN
          </Button>
        </Typography>
      </div>
  )
}
