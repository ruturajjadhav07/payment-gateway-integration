"use client";

import { useState } from "react";
import Link from "next/link";
import api from "../service/axios";
import { toast } from "react-toastify";
import { useRouter } from "next/navigation";

function UserLogin() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      await api.post(
        "/login",
        {
          username,
          password,
        },
        {
          withCredentials: true,
        },
      );

      toast.success("Login successful");
      router.push("/products");
    } catch (error: any) {
      const message = error?.response?.data?.message || "Login failed";

      toast.error(message);
    }
  };

  return (
    <div className="min-h-screen grid md:grid-cols-2">
      {/* Left Section */}

      <div className="hidden md:flex flex-col justify-center items-center bg-gradient-to-r from-blue-600 via-indigo-600 to-purple-600 text-white p-10">
        <h1 className="text-5xl font-bold mb-4">OrderFlow</h1>

        <p className="text-lg opacity-90 text-center max-w-md">
          A modern platform to manage your products and orders efficiently.
        </p>
      </div>

      {/* Login Form */}

      <div className="flex items-center justify-center bg-gray-50 px-6">
        <div className="bg-white p-10 rounded-2xl shadow-xl w-full max-w-md border border-gray-100">
          <h2 className="text-3xl font-bold text-center text-gray-800 mb-2">
            Welcome Back
          </h2>

          <p className="text-gray-500 text-center mb-8">
            Login to your account
          </p>

          <form onSubmit={handleSubmit} className="space-y-5">
            <input
              type="text"
              id="username"
              name="username"
              placeholder="Username"
              className="username w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 transition"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />

            <input
              type="password"
              id="password"
              name="password"
              placeholder="Password"
              className="password w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 transition"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />

            <button
              type="submit"
              id="loginButton"
              name="loginButton"
              className="w-full py-3 bg-gradient-to-r from-blue-600 to-indigo-600 text-white rounded-lg font-semibold hover:scale-[1.02] hover:shadow-lg transition duration-200 cursor-pointer"
            >
              Login
            </button>
          </form>

          <p className="text-center mt-5 text-gray-600" id="button">
            Don’t have an account?{" "}
            <Link
              id="register-link"
              href="/register"
              className="text-blue-600 font-medium hover:underline"
            >
              Register
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default UserLogin;
