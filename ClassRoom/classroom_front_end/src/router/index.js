import Vue from 'vue'
import App from '../App'
import Router from 'vue-router'
import login from '@/view/login'
import loginUp from '@/view/loginUp'
import mainStudent from '@/view/mainStudent'
import mainTeacher from '@/view/mainTeacher'
import coursePage from '@/view/coursePage'
import coursePageStudent from '@/view/coursePageStudent'
import topic from '@/view/coursePage/topic'
import topicTeacher from '@/view/coursePage/topicTeacher'
import scoreList from '@/view/coursePage/scoreList'
import scoreListStudent from '@/view/coursePage/scoreListStudent'
import teamOfStudent from '@/view/coursePage/teamOfStudent'
import teamOfStudentStudent from '@/view/coursePage/teamOfStudentStudent'
import teamOfTeacher from '@/view/coursePage/teamOfTeacher'
import workList from '@/view/coursePage/workList'
import workListStudent from '@/view/coursePage/workListStudent'
import workDetails from '@/view/workDetails'
import VIP from '@/view/VIP'
import markingHomework from '@/view/markingHomework'
import upFile from '@/view/upFile'


Vue.use(Router)

export default new Router({
  routes: [{
      path: '/',
      redirect: '/login'
    },
    {
      path: 'upFile',
      name: 'upFile',
      component: upFile,
    },
    {
      path: '/loginUp',
      name: 'loginUp',
      component: loginUp,
    },

    {
      path: '/login',
      name: 'login',
      component: login,
    },

    {
      path: '/mainTeacher/:uid',
      name: 'mainTeacher',
      component: mainTeacher,

    },

    {
      path: '/mainStudent/:uid',
      name: 'mainStudent',
      component: mainStudent,

    },

    {
      path: '/VIP',
      name: 'VIP',
      component: VIP,
    },

    {
      path: '/markingHomework/:wid/:cid/:uid',
      name: 'markingHomework',
      component: markingHomework,
    },

    {
      path: '/workDetails/:wid/:uid',
      name: 'workDetails',
      component: workDetails,
    },

    {
      path: '/coursePage/:cid/:uid',
      name: 'coursePage',
      component: coursePage,
      children: [{
          path: 'workList',
          name: 'workList',
          component: workList,
        },

        {
          path: 'topicTeacher',
          name: 'topicTeacher',
          component: topicTeacher,
        },
        {
          path: 'teamOfStudent',
          name: 'teamOfStudent',
          component: teamOfStudent,
        },
        {
          path: 'teamOfTeacher',
          name: 'teamOfTeacher',
          component: teamOfTeacher,
        },
        {
          path: 'scoreList',
          name: 'scoreList',
          component: scoreList,
        },

      ]
    },
    {
      path: '/coursePageStudent/:cid/:uid',
      name: 'coursePageStudent',
      component: coursePageStudent,
      children: [{
          path: 'workListStudent',
          name: 'workListStudent',
          component: workListStudent,
        },

        {
          path: 'topic',
          name: 'topic',
          component: topic,
        },

        {
          path: 'teamOfStudentStudent',
          name: 'teamOfStudentStudent',
          component: teamOfStudentStudent,
        },

        {
          path: 'teamOfTeacher',
          name: 'teamOfTeacher',
          component: teamOfTeacher,
        },

        {
          path: 'scoreListStudent',
          name: 'scoreListStudent',
          component: scoreListStudent,
        },
      ]
    },

  ]
})
