"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import api from "../service/axios";
import useAuth from "../lib/useAuth";
import LogoutButton from "../userComponent/LogoutButton";

interface Product {
  productId: number;
  productName: string;
  productDescription: string;
  productPrice: number;
  productQuantity: number;
  productImage: string;
}

export default function ProductsPage() {
  const { user, loading } = useAuth();
  const router = useRouter();

  const [products, setProducts] = useState<Product[]>([]);
  const [dataLoading, setDataLoading] = useState(true);

  useEffect(() => {
    if (!loading && !user) {
      router.push("/login");
    }
  }, [user, loading]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = await api.get("/products", {
          withCredentials: true,
        });

        setProducts(res.data);
      } catch {
        console.error("Failed to load products");
      } finally {
        setDataLoading(false);
      }
    };

    if (user) fetchProducts();
  }, [user]);

  if (loading || dataLoading) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-gray-50">
        <div className="w-10 h-10 border-4 border-blue-600 border-t-transparent rounded-full animate-spin"></div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Navbar */}

      <nav className="bg-white border-b shadow-sm">
        <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">
          <div className="flex items-center gap-3">
            <h1 className="text-xl font-bold text-gray-800">OrderFlow</h1>
          </div>

          <LogoutButton />
        </div>
      </nav>

      {/* Products */}

      <div className="max-w-7xl mx-auto px-6 py-12">
        {products.length === 0 ? (
          <div className="text-center py-20">
            <h2 className="text-xl font-semibold text-gray-700">
              No Products Available
            </h2>

            <p className="text-gray-500 mt-2">
              Products will appear here once added.
            </p>
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
            {products.map((product) => (
              <div
                key={product.productId}
                className="bg-white rounded-2xl shadow-md hover:shadow-xl transition duration-300 overflow-hidden group border border-gray-100"
              >
                {/* Product Image */}

                <div className="overflow-hidden">
                  <img
                    src={product.productImage}
                    alt={product.productName}
                    className="w-full h-52 object-cover group-hover:scale-105 transition duration-300"
                  />
                </div>

                {/* Product Info */}

                <div className="p-5">
                  <h2 className="text-lg font-semibold text-gray-800">
                    {product.productName}
                  </h2>

                  <p className="text-gray-500 text-sm mt-2 line-clamp-2">
                    {product.productDescription}
                  </p>

                  <div className="flex justify-between items-center mt-4">
                    <p className="text-lg font-bold bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
                      ₹{product.productPrice}
                    </p>

                    <span className="text-xs text-gray-500">
                      Stock {product.productQuantity}
                    </span>
                  </div>

                  <button className="mt-5 w-full py-2.5 bg-gradient-to-r from-blue-600 via-indigo-600 to-purple-600 text-white rounded-lg font-medium hover:shadow-lg hover:scale-[1.02] transition cursor-pointer">
                    Add to Cart
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
