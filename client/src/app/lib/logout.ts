import api from "../service/axios";

export const logoutUser = async () => {
  await api.post("/logout", {}, { withCredentials: true });
};