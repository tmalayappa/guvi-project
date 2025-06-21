<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f4f8;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            padding: 2.5rem;
            border-radius: 1rem;
            box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 28rem;
        }
        .form-label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 600;
            color: #334155;
        }
        .form-input {
            width: 100%;
            padding: 0.75rem 1rem;
            border: 1px solid #cbd5e1;
            border-radius: 0.5rem;
            margin-bottom: 1rem;
            font-size: 1rem;
            line-height: 1.5;
            transition: border-color 0.2s ease-in-out;
        }
        .form-input:focus {
            outline: none;
            border-color: #3b82f6;
            box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.25);
        }
        .btn-primary {
            background-color: #3b82f6;
            color: #ffffff;
            padding: 0.75rem 1.5rem;
            border-radius: 0.5rem;
            font-weight: 600;
            text-align: center;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
            border: none;
            width: 100%;
        }
        .btn-primary:hover {
            background-color: #2563eb;
        }
        .link-button {
            display: inline-block;
            margin-top: 1rem;
            padding: 0.5rem 1rem;
            background-color: #60a5fa;
            color: white;
            border-radius: 0.5rem;
            text-decoration: none;
            transition: background-color 0.2s ease-in-out;
        }
        .link-button:hover {
            background-color: #3b82f6;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Add New Employee</h2>

        <form:form action="${pageContext.request.contextPath}/saveEmployee" method="post" modelAttribute="employee">
            <div class="mb-4">
                <label for="id" class="form-label">Employee ID:</label>
                <form:input path="id" id="id" class="form-input" required="true" />
            </div>
            <div class="mb-4">
                <label for="name" class="form-label">Employee Name:</label>
                <form:input path="name" id="name" class="form-input" required="true" />
            </div>
            <div class="mb-4">
                <label for="email" class="form-label">Employee Email:</label>
                <form:input type="email" path="email" id="email" class="form-input" required="true" />
            </div>
            <div class="mb-6">
                <label for="location" class="form-label">Location:</label>
                <form:input path="location" id="location" class="form-input" required="true" />
            </div>
            <button type="submit" class="btn-primary">Save Employee</button>
        </form:form>

        <p class="text-center text-green-600 mt-4">${message}</p>

        <div class="mt-6 flex justify-center space-x-4">
            <a href="${pageContext.request.contextPath}/displayAll" class="link-button">Display All Employees (View)</a>
            <a href="${pageContext.request.contextPath}/api/employees" class="link-button" target="_blank">View All Employees (REST API)</a>
        </div>
        <p class="text-sm text-gray-500 text-center mt-4">
            For individual employee API: <code>/api/employees/{id}</code> (e.g., /api/employees/EMP001)
        </p>
    </div>
</body>
</html>