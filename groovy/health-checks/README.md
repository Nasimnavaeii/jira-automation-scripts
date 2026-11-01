
# Health Check & Project Status Scripts

This directory contains Groovy scripts designed for **Atlassian Jira Data Center & Server (ScriptRunner)** to perform health checks, status audits, and progress tracking across projects.

## 🚀 Overview
The scripts in this section help Jira administrators monitor active projects, assess their overall health based on issue statuses (e.g., Done vs. In Progress), and generate clean, responsive HTML dashboards directly within ScriptRunner custom REST endpoints.

---

## 📋 1. Project Health Check (`ProjectHealthCheck.groovy`)

This script audits all Jira projects, calculates completion metrics, flags inactive or risky projects, and renders a stylish HTML dashboard.

### ✨ Key Features
- **Dynamic Scoring:** Automatically calculates project completion percentages based on issue workflows.
- **Exclusion Filters:** Easily exclude specific project keys (e.g., system, archive, or internal utility projects) from the health check.
- **Self-Contained Dashboard:** Renders a fully styled HTML page with CSS embedded, requiring no external asset files.
- **REST Endpoint Ready:** Designed to be mounted on a ScriptRunner Custom REST Endpoint.

### 🛠️ Prerequisites & Dependencies
1. **Atlassian Jira Data Center / Server** (Tested on v11+)
2. **Adaptavist ScriptRunner for Jira** installed and licensed.
3. **Admin Permissions** or appropriate access to configure REST Endpoints.

### ⚙️ Configuration & Installation
1. Navigate to **Manage apps** -> **ScriptRunner** -> **REST Endpoints**.
2. Create a new Custom REST Endpoint.
3. Set the HTTP method to `GET` and use a relative path like:
   `projectHealthDashboard`
4. Paste the contents of `ProjectHealthCheck.groovy` into the script editor.
5. Adjust the `excludedProjects` list inside the code if you want to filter out specific project keys.
6. Access your dashboard via:
   `https://<your-jira-base-url>/rest/scriptrunner/latest/custom/projectHealthDashboard`

---

## 🛡️ License
This project is licensed under the MIT License - see the [LICENSE](../../LICENSE) file for details.
