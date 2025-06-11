import React from "react";
import "./Input.css";

function Input({
  type = "text",
  value,
  onChange,
  placeholder,
  className = "",
  ...props
}) {
  return (
    <input
      type={type}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
      className={`custom-input ${className}`}
      {...props}
    />
  );
}

export default Input;
