/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        "primary": "#00ADB5",
        "secondary": "#393E46",
        "foreground": "#222831",
        "background": "#EEEEEE",
      },
      fontFamily: {
        sans: ["Josefin Sans", "sans-serif"],
        serif: ["Playfair", "serif"],
      },
      fontSize: {
        "heading": "3rem",
        "subheading": "1.5rem",
      },
    },
  },
  plugins: [],
}

