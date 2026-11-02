/**
 * Script Name: Bulk Add Group to Project Role
 * Description: Adds a specific user group to a designated Project Role across all projects in Jira.
 *              Particularly useful for Jira 11 where default role schemes only affect newly created projects.
 * Compatible with: Atlassian Jira Data Center & Server 11.x + ScriptRunner
 */

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.security.roles.ProjectRoleManager
import com.atlassian.jira.bc.projectroles.ProjectRoleService
import com.atlassian.jira.util.SimpleErrorCollection

// --- CONFIGURATION ---
Long targetRoleId = 10600L                     // Replace with your target Project Role ID
String targetGroupName = "Scrum Master"         // Replace with your Jira Group name
String actorType = "atlassian-group-role-actor" // Standard group role actor type in Jira 11+
// ---------------------

def projectManager = ComponentAccessor.projectManager
def projectRoleManager = ComponentAccessor.getComponent(ProjectRoleManager)
def projectRoleService = ComponentAccessor.getComponent(ProjectRoleService)

def targetRole = projectRoleManager.getProjectRole(targetRoleId)

if (!targetRole) {
    return "Error: Project Role with ID ${targetRoleId} not found!"
}

int updatedCount = 0
List<String> failedProjects = []
List<String> updatedProjects = []

// Iterate through all projects and assign the group to the role
projectManager.getProjects().each { project ->
    SimpleErrorCollection errorCollection = new SimpleErrorCollection()
    try {
        projectRoleService.addActorsToProjectRole(
            [targetGroupName], 
            targetRole, 
            project, 
            actorType, 
            errorCollection
        )
        
        if (!errorCollection.hasAnyErrors()) {
            updatedCount++
            updatedProjects.add(project.key)
        } else {
            failedProjects.add("${project.key}: ${errorCollection.getErrors()}")
        }
    } catch (Exception e) {
        failedProjects.add("${project.key}: ${e.message}")
    }
}

// Return execution summary
return """
=== Bulk Role Update Completed ===
Total successfully updated projects: ${updatedCount}
Updated Project Keys: ${updatedProjects.join(', ')}
Errors encountered: ${failedProjects ? failedProjects.join(' | ') : 'None'}
"""
