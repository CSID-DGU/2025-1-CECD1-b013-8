import React from "react";
import "./Button.css";

function Button({
  children,
  onClick,
  type = "button",
  className = "",
  ...props
}) {
  return (
    <button
      type={type}
      onClick={onClick}
      className={`custom-button ${className}`}
      {...props}
    >
      {children}
    </button>
  );
}

export default Button;
