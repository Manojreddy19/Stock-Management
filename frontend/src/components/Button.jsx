

const Button = ({action , name}) => {
  return (
  <>
  <button  onclick={action}  value={name}></button>
  
  </>
  )
}

export default Button
