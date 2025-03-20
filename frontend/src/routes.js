import Home from './components/Home'
import TransferCreate from './components/Transfer/TransferCreate'
import Transfers from './components/Transfer/Transfers'


export const routes = [
    { path: '/', component: Home},
    { path: '/create', component: TransferCreate},
    { path: '/balance', component: Transfers}
];