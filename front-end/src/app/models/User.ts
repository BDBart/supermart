export interface User {
  email: string;
  password: string;
  akkoord: boolean;
  id?: number;
  adres?: string;
  postcode?: string;
  woonplaats?: string;
  m_afhalen: boolean;
  t_afhalen: boolean;
  versturen: boolean;
  rembours: boolean;

}
