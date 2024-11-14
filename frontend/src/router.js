
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ApplicationComplaintManager from "./components/listers/ApplicationComplaintCards"
import ApplicationComplaintDetail from "./components/listers/ApplicationComplaintDetail"

import Issue발급Manager from "./components/listers/Issue발급Cards"
import Issue발급Detail from "./components/listers/Issue발급Detail"

import Alarm알림Manager from "./components/listers/Alarm알림Cards"
import Alarm알림Detail from "./components/listers/Alarm알림Detail"

import IntegrationIntegrationManager from "./components/listers/IntegrationIntegrationCards"
import IntegrationIntegrationDetail from "./components/listers/IntegrationIntegrationDetail"

import EdmsEdmsManager from "./components/listers/EdmsEdmsCards"
import EdmsEdmsDetail from "./components/listers/EdmsEdmsDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/applications/complaints',
                name: 'ApplicationComplaintManager',
                component: ApplicationComplaintManager
            },
            {
                path: '/applications/complaints/:id',
                name: 'ApplicationComplaintDetail',
                component: ApplicationComplaintDetail
            },

            {
                path: '/issues/발급',
                name: 'Issue발급Manager',
                component: Issue발급Manager
            },
            {
                path: '/issues/발급/:id',
                name: 'Issue발급Detail',
                component: Issue발급Detail
            },

            {
                path: '/alarms/알림',
                name: 'Alarm알림Manager',
                component: Alarm알림Manager
            },
            {
                path: '/alarms/알림/:id',
                name: 'Alarm알림Detail',
                component: Alarm알림Detail
            },

            {
                path: '/integrations/integrations',
                name: 'IntegrationIntegrationManager',
                component: IntegrationIntegrationManager
            },
            {
                path: '/integrations/integrations/:id',
                name: 'IntegrationIntegrationDetail',
                component: IntegrationIntegrationDetail
            },

            {
                path: '/edms/edms',
                name: 'EdmsEdmsManager',
                component: EdmsEdmsManager
            },
            {
                path: '/edms/edms/:id',
                name: 'EdmsEdmsDetail',
                component: EdmsEdmsDetail
            },



    ]
})
