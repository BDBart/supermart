export interface User {
  id?: number;
  email: string;
  password: string;
  akkoord?: boolean;
  adres?: string;
  postcode?: string;
  woonplaats?: string;
  m_afhalen?: boolean;
  t_afhalen?: boolean;
  versturen?: boolean;
  rembours?: boolean;

}
