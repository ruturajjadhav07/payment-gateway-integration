"use client";

import Link from "next/link";
import { useState } from "react";
import { toast } from "react-toastify";
import api from "../service/axios";

interface FormData {
  username: string;
  email: string;
  password: string;
  number: string;
}

function UserRegister() {
  const [formData, setFormData] = useState<FormData>({
    username: "",
    email: "",
    password: "",
    number: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await api.post("/register", formData);

      const message = response.data?.message || "Registration successful";

      toast.success(message);

      setFormData({
        username: "",
        email: "",
        password: "",
        number: "",
      });
    } catch (error: any) {
      if (error.response && error.response.data) {
        const backendMessage =
          error.response.data.message || "Registration failed";

        toast.error(backendMessage);
      } else {
        toast.error("An unexpected error occurred. Please try again.");
      }

      console.error("Registration error:", error);
    }
  };

  return (
    <div className="min-h-screen grid md:grid-cols-2">
      {/* Left Section */}

      <div className="hidden md:flex flex-col justify-center items-center bg-gradient-to-r from-blue-600 via-indigo-600 to-purple-600 text-white p-10">
        <h1 className="text-5xl font-bold mb-4">OrderFlow</h1>

        <p className="text-lg opacity-90 text-center max-w-md">
          Create an account and start managing your products and orders easily.
        </p>
      </div>

      {/* Register Form */}

      <div className="flex items-center justify-center bg-gray-50 px-6">
        <div className="bg-white p-10 rounded-2xl shadow-xl w-full max-w-md border border-gray-100">
          <h2 className="text-3xl font-bold text-center text-gray-800 mb-2">
            Create Account
          </h2>

          <p className="text-gray-500 text-center mb-8">
            Register to get started
          </p>

          <form onSubmit={handleSubmit} className="space-y-5">
            <input
              type="text"
              id="username"
              name="username"
              placeholder="Username"
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 transition"
              required
              value={formData.username}
              onChange={handleChange}
            />

            <input
              type="email"
              id="email"
              name="email"
              placeholder="Email"
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 transition"
              required
              value={formData.email}
              onChange={handleChange}
            />

            <input
              type="tel"
              id="number"
              name="number"
              placeholder="Phone Number"
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 transition"
              required
              value={formData.number}
              onChange={handleChange}
            />

            <input
              type="password"
              id="password"
              name="password"
              placeholder="Password"
              className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 transition"
              required
              value={formData.password}
              onChange={handleChange}
            />

            <button
              type="submit"
              id="registerButton"
              name="registerButton"
              className="w-full py-3 bg-gradient-to-r from-blue-600 to-indigo-600 text-white rounded-lg font-semibold hover:scale-[1.02] hover:shadow-lg transition duration-200 cursor-pointer"
            >
              Register
            </button>
          </form>

          <p className="text-center mt-5 text-gray-600">
            Already have an account?{" "}
            <Link
              href="/login"
              className="text-blue-600 font-medium hover:underline"
            >
              Login
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default UserRegister;
