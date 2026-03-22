"use client";

import Link from "next/link";
import useAuth from "../lib/useAuth";
import ProductsPage from "../products/page";
import { FaGithub, FaLinkedin, FaInstagram, FaTwitter } from "react-icons/fa";

export default function Home() {
  const { user, loading } = useAuth();

  if (loading) {
    return <p className="p-10 text-lg text-center">Loading...</p>;
  }

  if (user) {
    return <ProductsPage />;
  }

  return (
    <main className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100">
      {/* Navbar */}
      <nav className="flex justify-between items-center px-10 py-4 bg-white/80 backdrop-blur-md shadow-sm border-b sticky top-0 z-50">
        <div className="flex items-center gap-3">
          <h1 className="text-2xl font-bold bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
            OrderFlow
          </h1>
        </div>

        <div className="space-x-4">
          <Link
            href="/login"
            className="px-4 py-2 font-medium text-gray-700 hover:text-blue-600 transition"
            id="login"
          >
            Login
          </Link>

          <Link
            href="/register"
            className="px-5 py-2 rounded-lg text-white bg-gradient-to-r from-blue-600 to-indigo-600 hover:opacity-90 transition shadow-md"
          >
            Sign Up
          </Link>
        </div>
      </nav>

      {/* Hero Section */}
      <section className="flex flex-col items-center justify-center text-center px-6 py-28 bg-gradient-to-r from-blue-50 via-indigo-50 to-purple-50">
        <h2 className="text-4xl md:text-5xl font-bold text-gray-800 mb-6 leading-tight">
          Simplify Your{" "}
          <span className="text-blue-600">Ordering Experience</span>
        </h2>

        <p className="text-lg text-gray-600 max-w-2xl mb-10">
          OrderFlow helps users browse products, place orders, and make secure
          payments while giving admins powerful tools to manage products and
          track orders easily.
        </p>

        <div className="flex gap-4">
          <Link
            href="/register"
            className="px-7 py-3 text-lg text-white rounded-lg bg-gradient-to-r from-blue-600 to-indigo-600 hover:scale-105 transition shadow-lg"
          >
            Get Started
          </Link>

          <Link
            href="/login"
            className="px-7 py-3 text-lg border border-blue-600 text-blue-600 rounded-lg hover:bg-blue-50 transition"
          >
            Login
          </Link>
        </div>
      </section>

      {/* Features */}
      <section className="px-10 py-24 bg-white">
        <h3 className="text-3xl font-bold text-center text-gray-800 mb-16">
          Why Choose OrderFlow?
        </h3>

        <div className="grid md:grid-cols-3 gap-10 max-w-6xl mx-auto">
          <div className="p-8 bg-gradient-to-br from-blue-50 to-white rounded-2xl shadow-sm hover:shadow-xl hover:-translate-y-1 transition">
            <h4 className="text-xl font-semibold mb-3 text-blue-600">
              ⚡ Fast Ordering
            </h4>
            <p className="text-gray-600">
              Quickly browse products, add items to your cart, and complete
              orders with a seamless checkout process.
            </p>
          </div>

          <div className="p-8 bg-gradient-to-br from-indigo-50 to-white rounded-2xl shadow-sm hover:shadow-xl hover:-translate-y-1 transition">
            <h4 className="text-xl font-semibold mb-3 text-indigo-600">
              🔒 Secure Payments
            </h4>
            <p className="text-gray-600">
              Integrated payment system ensures safe and reliable transactions
              with strong security.
            </p>
          </div>

          <div className="p-8 bg-gradient-to-br from-purple-50 to-white rounded-2xl shadow-sm hover:shadow-xl hover:-translate-y-1 transition">
            <h4 className="text-xl font-semibold mb-3 text-purple-600">
              📦 Easy Admin Control
            </h4>
            <p className="text-gray-600">
              Manage products, update inventory, and monitor orders with an
              intuitive admin dashboard.
            </p>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="flex justify-between items-center px-10 py-6 bg-gradient-to-r from-gray-100 to-gray-200 text-gray-600">
        <p>
          © {new Date().getFullYear()}{" "}
          <span className="font-semibold">OrderFlow</span>. All rights reserved.
        </p>

        {/* Social Icons */}
        <div className="flex gap-5 text-xl">
          <a
            href="https://github.com/ruturajjadhav07"
            target="_blank"
            className="hover:text-black transition"
          >
            <FaGithub />
          </a>

          <a
            href="https://www.linkedin.com/in/ruturaj-jadhav-0a250821b/"
            target="_blank"
            className="hover:text-blue-600 transition"
          >
            <FaLinkedin />
          </a>

          <a
            href="https://www.instagram.com/ruturajj_07/"
            target="_blank"
            className="hover:text-pink-500 transition"
          >
            <FaInstagram />
          </a>

          <a
            href="https://x.com/spoiidermon"
            target="_blank"
            className="hover:text-sky-500 transition"
          >
            <FaTwitter />
          </a>
        </div>
      </footer>
    </main>
  );
}
