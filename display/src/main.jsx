import React from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import Home from './pages/Home'
import './index.css'
import Transaction from './pages/Transaction'

const router = createBrowserRouter([
  { path: "/", element: <Home /> },
  { path: "/transactions/:id", element: <Transaction /> },
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
