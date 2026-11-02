# Bulk Add Group to Project Roles

This Groovy script for **Atlassian Jira Data Center & Server (ScriptRunner)** allows administrators to bulk-assign a specific Jira group to a project role across **all existing projects**.

## 🎯 The Challenge & Use Case
In Jira, configuring default members in **Manage Default Members** / Project Role Schemes typically applies only to **newly created projects**. 
Legacy or existing projects often lack these role associations, leading to manual overhead when introducing new administrative or agile groups (e.g., Scrum Masters, DevOps teams).

Furthermore, in **Jira 11**, API changes (such as the removal of the `TYPE` constant in `GroupRoleActorFactory`) can break legacy scripts. This script uses the official `ProjectRoleService` with the explicit string actor type (`atlassian-group-role-actor`) to safely execute bulk updates on Jira 11+.

---

## 🛠️ Prerequisites & Compatibility
- **Jira Data Center / Server:** Tested on v11.3+ (Compatible with v9/v10 as well).
- **Add-on:** Adaptavist ScriptRunner for Jira.
- **Permissions:** Jira System Administrator access to run scripts in the Script Console.

---

## ⚙️ How to Use
1. Go to **Administration** -> **Manage apps** -> **ScriptRunner** -> **Script Console**.
2. Update the configuration variables at the top of `AddGroupToProjectRole.groovy`:
   - `targetRoleId`: The ID of your project role (e.g., `10600L`).
   - `targetGroupName`: The exact name of the Jira group to be added.
3. Paste the script into the console and click **Run**.
4. Review the returned execution log for successful project keys and any errors.

---

## 🛡️ License
This project is licensed under the MIT License - see the [LICENSE](../../LICENSE) file for details.
