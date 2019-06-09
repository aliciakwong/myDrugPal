package com.example.mydrugpal;

public class Profile
{
    public String m_FirstName;
    private String m_LastName;
    private String m_Email;
    private String m_Password;

    public Profile (String firstName, String lastName, String email, String pass)
    {
        m_FirstName = firstName;
        m_LastName = lastName;
        m_Email = email;
        m_Password = pass;
    }

    public String GetFirstName()
    {
        return m_FirstName;
    }

    public String GetLastName()
    {
        return m_LastName;
    }

    public String GetEmail()
    {
        return m_Email;
    }

    public String GetPassword()
    {
        return m_Password;
    }
}