package com.carconnect.Service;

import com.carconnect.entity.*;
import com.carconnect.exception.*;

public interface IAdminService {
	void getAdminById(int adminid);

	void getAdminByUsername(String username);

	void registerAdmin(Admin admin);

	void updateAdmin(Admin admin);
	
	boolean Authenticate(int adminid,String password);

	void deleteAdmin(int adminid, String password);
}
