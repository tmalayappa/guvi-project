<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Employees</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f4f8;
            padding: 2rem;
        }
        .container {
            background-color: #ffffff;
            padding: 2.5rem;
            border-radius: 1rem;
            box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
            max-width: 60rem;
            margin: 2rem auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1.5rem;
        }
        th, td {
            padding: 0.75rem;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }
        th {
            background-color: #f8fafc;
            font-weight: 600;
            color: #475569;
        }
        tr:hover {
            background-color: #f0f9ff;
        }
        .back-button {
            display: inline-block;
            margin-top: 1rem;
            padding: 0.5rem 1rem;
            background-color: #60a5fa;
            color: white;
            border-radius: 0.5rem;
            text-decoration: none;
            transition: background-color 0.2s ease-in-out;
        }
        .back-button:hover {
            background-color: #3b82f6;
        }
        .details-link {
            color: #3b82f6;
            text-decoration: none;
            font-weight: 500;
        }
        .details-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">All Employees</h2>

        <c:if test="${empty employees}">
            <p class="text-center text-gray-600">No employees found.</p>
        </c:if>
        <c:if test="${not empty employees}">
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200 rounded-lg">
                    <thead class="bg-gray-50">
                        <tr>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider rounded-tl-lg">ID</th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider rounded-tr-lg">Location</th>
                            <th scope="col" class="relative px-6 py-3 rounded-tr-lg">
                                <span class="sr-only">View</span>
                            </th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                        <c:forEach var="employee" items="${employees}">
                            <tr>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${employee.id}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${employee.name}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${employee.email}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${employee.location}</td>
                                <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                    <a href="${pageContext.request.contextPath}/display/${employee.id}" class="details-link">View Details</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <div class="flex justify-center mt-6">
            <a href="${pageContext.request.contextPath}/" class="back-button">Back to Home</a>
        </div>
    </div>
</body>
</html>