"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import useAuth from "../lib/useAuth";
import UserLogin from "../userComponent/UserLogin";

export default function LoginPage() {
  const { user, loading } = useAuth();
  const router = useRouter();

  useEffect(() => {
    if (!loading && user) {
      router.push("/products");
    }
  }, [user, loading]);

  if (loading) return null;

  return <UserLogin />;
}
