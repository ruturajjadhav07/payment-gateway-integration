"use client";

import { useRouter } from "next/navigation";
import api from "../service/axios";

export default function LogoutButton() {

  const router = useRouter();

  const handleLogout = async () => {
    try {

      await api.post("/logout", {}, {
        withCredentials: true
      });

      router.push("/");
      router.refresh();

    } catch (error) {
      console.error("Logout failed");
    }
  };

  return (
    <button onClick={handleLogout} 
    id="logoutButton"
    className=" px-4 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600 hover:shadow transition font-medium cursor-pointer" >
    Logout
    </button>
      
  );
}