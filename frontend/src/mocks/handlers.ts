import { rest } from 'msw';
import { hot, relate, social, recommend } from '@/mocks/data';

export const handlers = [
  // 키워드
  rest.get('/keyword/hot', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(hot));
  }),

  rest.get('/keyword/relate', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(relate));
  }),

  rest.get('/keyword/recommend', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(recommend));
  }),

  // 분석
  rest.get('/analyze/social', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(social));
  }),

  rest.post('/member/login', (req, res, ctx) => {
    return res(ctx.status(500));
  }),
];
