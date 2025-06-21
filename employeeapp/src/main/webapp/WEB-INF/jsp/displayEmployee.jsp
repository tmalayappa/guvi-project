<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
    <link href="https://cdn.jsdelivr.net/npm/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
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
            text-align: center;
        }
        .detail-item {
            display: flex;
            justify-content: space-between;
            padding: 0.75rem 0;
            border-bottom: 1px solid #e2e8f0;
            margin-bottom: 0.5rem;
        }
        .detail-label {
            font-weight: 600;
            color: #475569;
        }
        .detail-value {
            color: #334155;
        }
        .back-button {
            display: inline-block;
            margin-top: 2rem;
            padding: 0.75rem 1.5rem;
            background-color: #60a5fa;
            color: white;
            border-radius: 0.5rem;
            text-decoration: none;
            transition: background-color 0.2s ease-in-out;
        }
        .back-button:hover {
            background-color: #3b82f6;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Employee Details</h2>

        <c:if test="${not empty employee}">
            <div class="detail-item">
                <span class="detail-label">Employee ID:</span>
                <span class="detail-value">${employee.id}</span>
            </div>
            <div class="detail-item">
                <span class="detail-label">Name:</span>
                <span class="detail-value">${employee.name}</span>
            </div>
            <div class="detail-item">
                <span class="detail-label">Email:</span>
                <span class="detail-value">${employee.email}</span>
            </div>
            <div class="detail-item">
                <span class="detail-label">Location:</span>
                <span class="detail-value">${employee.location}</span>
            </div>
        </c:if>
        <c:if test="${empty employee && not empty message}">
            <p class="text-center text-red-600 mb-4">${message}</p>
        </c:if>

        <a href="${pageContext.request.contextPath}/displayAll" class="back-button">Back to All Employees</a>
        <a href="${pageContext.request.contextPath}/" class="back-button ml-4">Back to Home</a>
    </div>
</body>
</html>